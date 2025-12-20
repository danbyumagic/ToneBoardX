# ToneBoard Android - Architecture Documentation

## Overview

This document describes the architecture and design decisions for the ToneBoard Android port. ToneBoard is a Chinese input method that requires users to type pinyin with tone numbers to help them learn and remember Mandarin tones.

## Architecture Pattern

The app follows a **layered architecture** with clear separation of concerns:

```
┌─────────────────────────────────────┐
│         UI Layer                    │
│  (Activities, Layouts, Views)       │
├─────────────────────────────────────┤
│       Service Layer                 │
│     (IME Service)                   │
├─────────────────────────────────────┤
│       Business Logic                │
│  (Dictionary, Input Processing)     │
├─────────────────────────────────────┤
│         Data Layer                  │
│   (Dictionary Data, Preferences)    │
└─────────────────────────────────────┘
```

## Core Components

### 1. ToneBoardIME (Input Method Service)

**Purpose**: Main keyboard service that integrates with Android's IME framework

**Responsibilities**:
- Handle keyboard lifecycle (creation, visibility, input)
- Process key events
- Display candidate suggestions
- Commit text to the target application
- Manage keyboard view hierarchy

**Key Methods**:
```kotlin
onCreateInputView()      // Create keyboard UI
onStartInput()           // Initialize for new input session
onKey()                  // Handle individual key presses
updateCandidates()       // Refresh suggestion list
commitCandidate()        // Insert selected text
```

**Implementation Details**:
- Extends `InputMethodService` from Android framework
- Implements `KeyboardView.OnKeyboardActionListener` for key events
- Uses `InputConnection` to communicate with target app
- Maintains composition state in `currentInput` StringBuilder

### 2. PinyinDictionary

**Purpose**: Convert pinyin input (with tones) to Chinese characters

**Responsibilities**:
- Store pinyin → character mappings
- Find matching candidates for partial input
- Order results by relevance
- Validate tone markers

**Key Methods**:
```kotlin
getCandidates(input: String): List<String>
hasCompleteSyllable(input: String): Boolean
```

**Data Structure**:
```kotlin
private val dictionary = mapOf(
    "pinyin_with_tones" to listOf("字", "符", "选项")
)
```

**Algorithm**:
1. Convert input to lowercase
2. Try exact match first
3. Fall back to prefix matching
4. Return top 10 candidates
5. Remove duplicates

**Future Enhancements**:
- Load from external file (CC-CEDICT format)
- Implement frequency-based ranking
- Add caching for common queries
- Support multi-character word compounds

### 3. MainActivity

**Purpose**: Main entry point providing user onboarding and configuration

**Responsibilities**:
- Display app status (keyboard enabled/disabled)
- Provide quick access to:
  - Tutorial
  - Keyboard settings
  - App settings
  - System keyboard selector
- Check keyboard enablement status

**UI Components**:
- Status indicator
- Navigation cards (Material Design)
- Action buttons

### 4. TutorialActivity

**Purpose**: Interactive learning environment for keyboard practice

**Responsibilities**:
- Present step-by-step lessons
- Accept keyboard input
- Validate user responses
- Provide feedback
- Track progression

**Lesson Structure**:
```kotlin
data class Lesson(
    val expectedOutput: String,    // Chinese characters
    val expectedInput: String,     // pinyin with tones
    val instruction: String        // User guidance
)
```

**Flow**:
1. Display instruction
2. User types with ToneBoard
3. Check input against expected
4. Show feedback (correct/incorrect)
5. Advance to next lesson

### 5. SettingsActivity

**Purpose**: User preference management

**Responsibilities**:
- Display app settings
- Persist user preferences
- Provide app information

**Settings Categories**:
- Keyboard behavior (vibration, sound)
- About information

## Data Flow

### Input Processing Flow

```
User Tap
   ↓
KeyboardView
   ↓
onKey() Handler
   ↓
┌─────────────────┐
│ Is it a letter? │──No──→ Handle Special Key
└─────────────────┘        (space, delete, etc.)
   │ Yes
   ↓
Append to currentInput
   ↓
updateCandidates()
   ↓
PinyinDictionary.getCandidates()
   ↓
Display in Candidate View
   ↓
User selects candidate
   ↓
commitCandidate()
   ↓
InputConnection.commitText()
   ↓
Text appears in target app
```

### Keyboard Lifecycle

```
App Launched
   ↓
IME Service Created
   ↓
User enables in Settings
   ↓
User selects keyboard
   ↓
onCreateInputView() ──→ Create keyboard UI
   ↓
onStartInput() ──────→ Initialize state
   ↓
onStartInputView() ──→ Show keyboard
   ↓
User types...
   ↓
onFinishInput() ─────→ Clean up
   ↓
Keyboard hidden
```

## Key Design Decisions

### 1. Dictionary Implementation

**Decision**: In-memory map with string keys

**Rationale**:
- Simple to implement and understand
- Fast lookups for demonstration
- Easy to modify for testing

**Trade-offs**:
- Limited to small vocabulary
- All data in memory
- No persistence between sessions

**Future**: Replace with database (Room or SQLite) for production use

### 2. Input Composition

**Decision**: Use StringBuilder for input buffer

**Rationale**:
- Efficient string manipulation
- Easy to add/remove characters
- Clear state management

**Alternative Considered**: Use InputConnection's composing text
- Rejected because it's harder to control display

### 3. Candidate Display

**Decision**: Horizontal scrollable list

**Rationale**:
- Familiar pattern from other IMEs
- Efficient screen space usage
- Easy to implement with LinearLayout

**Trade-offs**:
- No multi-line candidates
- Limited visible candidates

### 4. Keyboard Layout

**Decision**: QWERTY with bottom row numbers

**Rationale**:
- Familiar to users
- Numbers easily accessible
- Standard Android Keyboard XML

**Alternative Considered**: Custom layout with tone buttons
- Rejected for simplicity in v1

### 5. Tutorial Implementation

**Decision**: In-app practice field

**Rationale**:
- Immediate practice without setup
- Safe testing environment
- User can try before enabling

**Trade-offs**:
- Duplicates some keyboard logic
- Can't test in real apps immediately

## Android IME Framework Integration

### Required Components

1. **Service Declaration** (AndroidManifest.xml)
```xml
<service
    android:name=".ToneBoardIME"
    android:permission="android.permission.BIND_INPUT_METHOD">
    <intent-filter>
        <action android:name="android.view.InputMethod" />
    </intent-filter>
    <meta-data
        android:name="android.view.im"
        android:resource="@xml/method" />
</service>
```

2. **Input Method Metadata** (res/xml/method.xml)
```xml
<input-method
    android:settingsActivity="..."
    android:isDefault="false" />
```

3. **Keyboard Layout** (res/xml/qwerty.xml)
```xml
<Keyboard>
    <Row>
        <Key android:codes="..." android:keyLabel="..." />
    </Row>
</Keyboard>
```

### IME Service Responsibilities

1. **Input View Management**
   - Create and return keyboard view
   - Handle view lifecycle
   - Update UI based on state

2. **Text Composition**
   - Track user input
   - Display composing text
   - Commit final text

3. **Candidate Management**
   - Generate suggestions
   - Display candidate view
   - Handle selection

4. **Input Connection**
   - Get text before/after cursor
   - Insert text
   - Delete text
   - Send key events

## UI/UX Design Principles

### Material Design

The app follows Material Design 3 guidelines:
- Card-based navigation in main screen
- Elevated components
- Consistent spacing (8dp grid)
- Material color system
- Ripple effects for interaction

### Keyboard Design

- **Clear Visual Hierarchy**: Keys clearly defined
- **Feedback**: Visual (highlight) and haptic (optional)
- **Discoverability**: Numbers visible on main layout
- **Consistency**: Similar to system keyboard

### Accessibility

Future improvements should include:
- TalkBack support
- Haptic feedback options
- Adjustable key size
- High contrast themes

## Performance Considerations

### Current Implementation

- In-memory dictionary (fast but limited)
- Synchronous candidate lookup
- No caching
- Direct UI updates

### Optimization Opportunities

1. **Dictionary Loading**
   - Load asynchronously on service start
   - Use coroutines for background work

2. **Candidate Generation**
   - Cache recent queries
   - Limit result count
   - Use efficient data structures

3. **Memory Management**
   - Clear candidates when hidden
   - Release resources when not in use
   - Monitor memory usage

4. **Rendering**
   - Reuse candidate views
   - Minimize layout passes
   - Use RecyclerView for candidates

## Testing Strategy

### Unit Tests

Test individual components:
- PinyinDictionary candidate matching
- Input validation logic
- State management

### Integration Tests

Test component interaction:
- IME service lifecycle
- Dictionary integration
- Preference loading

### UI Tests

Test user interactions:
- Tutorial completion
- Settings changes
- Keyboard enablement flow

### Manual Testing

Test on various devices:
- Different Android versions
- Different screen sizes
- Different OEM keyboards

## Security and Privacy

### Permissions

The app requires:
- `BIND_INPUT_METHOD`: Required for IME service
- No internet access (no analytics/tracking)
- No storage access (no data collection)

### Data Handling

- No user data collected
- No network requests
- Dictionary data bundled with app
- Settings stored locally

### Best Practices

- Follow Android security guidelines
- Request minimal permissions
- Transparent about data usage
- No third-party analytics

## Extensibility

### Adding New Features

The architecture supports:

1. **Additional Keyboards**
   - Create new XML layouts
   - Add layout selection logic
   - No IME code changes needed

2. **Traditional Chinese**
   - Add traditional character mappings
   - Create separate dictionary
   - Add language selector

3. **Custom Dictionaries**
   - User-defined words
   - Import/export functionality
   - Sync across devices

4. **Learning Features**
   - Track commonly missed tones
   - Provide tone hints
   - Show progress statistics

### Plugin Architecture

Future versions could support:
- Custom dictionary plugins
- Theme plugins
- Layout plugins

## Known Limitations

1. **Dictionary Size**: Current implementation limited to small vocabulary
2. **Word Segmentation**: No automatic word boundary detection
3. **Frequency Ranking**: No word frequency consideration
4. **Multi-syllable Words**: Limited support for phrases
5. **Context Awareness**: No sentence-level suggestions

## Migration Path

### From Demo to Production

Steps to enhance for production:

1. **Dictionary Enhancement**
   - Integrate CC-CEDICT data
   - Implement frequency rankings
   - Add word segmentation

2. **Performance Optimization**
   - Add caching layer
   - Implement lazy loading
   - Optimize memory usage

3. **Feature Additions**
   - Traditional Chinese support
   - Custom word management
   - Statistics tracking

4. **Polish**
   - More keyboard themes
   - Additional layouts
   - Improved animations

## Appendix: Code Conventions

### Naming

- Classes: PascalCase
- Functions: camelCase
- Variables: camelCase
- Constants: UPPER_SNAKE_CASE
- Resources: snake_case

### File Organization

- One class per file
- Group related classes in packages
- Resources organized by type

### Documentation

- KDoc comments for public APIs
- Inline comments for complex logic
- README for setup instructions

## References

- [Android IME Documentation](https://developer.android.com/develop/ui/views/touch-and-input/creating-input-method)
- [Material Design Guidelines](https://material.io/design)
- [Kotlin Coding Conventions](https://kotlinlang.org/docs/coding-conventions.html)
- [Original iOS ToneBoard](https://github.com/bellkev/ToneBoard)
